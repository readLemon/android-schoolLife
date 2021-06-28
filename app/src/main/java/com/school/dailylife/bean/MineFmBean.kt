package com.school.dailylife.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by chenyang
 * on 20-11-25
 */
data class MineFmBean(
    @SerializedName("accumulatedComment")
    val accumulatedComment: Int,
    @SerializedName("reputationCore")
    val reputationCore: Int,
    @SerializedName("innerComments")
    val commentWall: List<CommentWall>,
    @SerializedName("soldingNum")
    val soldingNum: Int,
    @SerializedName("soledNum")
    val soledNum: Int,
    @SerializedName("lost")
    val lostPickerBean: LostPickerBean
) {
    data class CommentWall(
        @SerializedName("commentContent")
        val commentContent: String,
        @SerializedName("commentatorAvatarPic")
        val commentatorAvatarPic: String,
        @SerializedName("uid")
        val commentatorUid: Int
    )
}
