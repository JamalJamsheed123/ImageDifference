package com.yesnet.imagediff.Models
import java.io.Serializable


data class GameModel(
    val game: ArrayList<GameModelClass>
): Serializable

data class GameModelClass (
    var Name: String? = null,
    var isUnlocked : Boolean? = null,
    var imageUrl: String? = null,
    var thumbnailUrl: String? = null,
    var differenceImageUrl: String? = null,

    var level: Int,
    var boundingBox: ArrayList<Box>?
    ): Serializable

data class Box (
    var x: Int,
    var y: Int,
    var width: Int,
    var isPass: Boolean = false,
    var height: Int
): Serializable