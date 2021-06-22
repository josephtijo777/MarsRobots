package com.example.marsrobots.network.response


import com.google.gson.annotations.SerializedName
import java.util.*

data class ImageFetchApiResponse(
    @SerializedName("collection")
    val collection: Collection
)

data class Collection(
    @SerializedName("items")
    val items: List<Item>
)

data class Item(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("links")
    val links: List<Link>
)

data class Link(
    @SerializedName("href")
    val href: String,
    @SerializedName("rel")
    val rel: String,
    @SerializedName("render")
    val render: String
)

data class Data(
    @SerializedName("date_created")
    val dateCreated: String,
    @SerializedName("description")
    val description: String
)