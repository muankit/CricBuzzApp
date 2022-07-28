package com.cricbuzz.data.cache

import android.content.res.Resources
import com.cricbuzz.data.entity.GenericEntity
import com.cricbuzz.data.entity.RestaurantEntity
import com.cricbuzz.data.mapper.mapRmListToEntityList
import com.cricbuzz.data.sdks.room.dao.MenuDao
import com.cricbuzz.data.sdks.room.dao.RestaurantsDao
import com.cricbuzz.data.sdks.room.entity.RmMenuEntity
import com.cricbuzz.data.sdks.room.entity.RmRestaurantEntity
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject

class RestaurantCacheImpl @Inject constructor(
    private val restaurantsDao: RestaurantsDao,
    private val menuDao: MenuDao,
    private val resources: Resources
) : RestaurantCache {
    override suspend fun fetchRestaurants(search: String): List<RestaurantEntity> {
        return mapRmListToEntityList(restaurantsDao.fetchRestaurant(search))
    }

    override suspend fun insertData(): GenericEntity {
        if (restaurantsDao.count() == 0) {
            restaurantsDao.insert(createRestaurantList())
        }

        if (menuDao.count() == 0) {
            menuDao.insert(createMenuList())
        }
        return GenericEntity(true)
    }

    private fun createRestaurantList(): List<RmRestaurantEntity> {
        val restaurants = resources.assets.open("restaurants.json").bufferedReader().use {
            JSONObject(it.readText())
        }
        val restaurantsArray = restaurants.getJSONArray("restaurants")

        val list = arrayListOf<RmRestaurantEntity>()

        for (i in 0 until restaurantsArray.length()) {
            val item = restaurantsArray.getJSONObject(i)

            val id = item.getString("id")
            val name = item.getString("name")
            val address = item.getString("address")
            val cuisine_type = item.getString("cuisine_type")

            val reviews = item.getJSONArray("reviews")

            Timber.d("$id $name $address $cuisine_type $reviews")

            list.add(
                RmRestaurantEntity(
                    id = id.toInt(),
                    name = name,
                    cuisine_type = cuisine_type,
                    reviews = reviews.toString()
                )
            )
        }

        return list
    }

    private fun createMenuList(): List<RmMenuEntity> {
        val list = arrayListOf<RmMenuEntity>()
        val restaurants = resources.assets.open("menu.json").bufferedReader().use {
            JSONObject(it.readText())
        }
        val menusArray = restaurants.getJSONArray("menus")

        for (i in 0 until menusArray.length()) {
            val restraId = menusArray.getJSONObject(i).getString("restaurantId")
            val categories = menusArray.getJSONObject(i).getJSONArray("categories")
            for (j in 0 until categories.length()) {
                val menuItems = categories.getJSONObject(j).getJSONArray("menu-items")

                for (k in 0 until menuItems.length()) {
                    val item = menuItems.getJSONObject(k)

                    list.add(
                        RmMenuEntity(
                            id = item.getString("id").toInt(),
                            restaurantId = restraId.toInt(),
                            name = item.getString("name"),
                            description = item.getString("description")
                        )
                    )
                }
            }
        }


        return list
    }
}
