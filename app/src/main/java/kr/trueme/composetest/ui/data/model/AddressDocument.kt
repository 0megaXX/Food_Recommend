package kr.trueme.composetest.ui.data.model

data class AddressDocument(
    val addressName: String,
    val addressType: String,
    val y: String,
    val x: String,
) {
    companion object {
        fun fromJson(json: Map<String, Any>): AddressDocument = AddressDocument(
            addressName = json["address_name"] as String,
            addressType = json["address_type"] as String,
            y = json["y"] as String,
            x = json["x"] as String,
        )
    }
}