package com.grupo7.brasilflixapp.model.favorites

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class Favorites(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "poster_path")
    var poster_path: String? = null,
    @ColumnInfo(name = "title")
    var title: String? = null
    ): Parcelable {
    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<Favorites> =
            object : DiffUtil.ItemCallback<Favorites>() {
                override fun areItemsTheSame(oldItem: Favorites, newItem: Favorites): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Favorites, newItem: Favorites): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
}
