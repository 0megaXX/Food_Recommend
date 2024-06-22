package kr.trueme.composetest.ui.data.model

data class Document(
    val id: String,
    val placeName: String,
    val categoryName: String?,
    val categoryGroupCode: String,
    val categoryGroupName: String,
    val phone: String,
    val addressName: String?,
    val roadAddressName: String,
    val x: String,
    val y: String,
    val placeUrl: String,
    val distance: String,
) {
    companion object {
        fun fromJson(json: Map<String, Any>): Document = Document(
            id = json["id"] as String,
            placeName = json["place_name"] as String,
            categoryName = json["category_name"] as String,
            categoryGroupCode = json["category_group_code"] as String,
            categoryGroupName = json["category_group_name"] as String,
            phone = json["phone"] as String,
            addressName = json["address_name"] as String?,
            roadAddressName = json["road_address_name"] as String,
            x = json["x"] as String,
            y = json["y"] as String,
            placeUrl = json["place_url"] as String,
            distance = json["distance"] as String,
        )
    }
}