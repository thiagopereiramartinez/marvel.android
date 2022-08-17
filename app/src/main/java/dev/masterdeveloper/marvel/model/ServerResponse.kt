package dev.masterdeveloper.marvel.model

data class ServerResponse<T>(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: Data<T>
) {

    data class Data<T>(
        val offset: Int,
        val limit: Int,
        val total: Int,
        val count: Int,
        val results: List<T>
    )

}
