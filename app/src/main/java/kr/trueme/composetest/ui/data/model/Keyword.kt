package kr.trueme.composetest.ui.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Keyword(
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
) : Parcelable {
}