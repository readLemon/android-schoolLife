package com.school.dailylife.bean

/**
 * Created by chenyang
 * on 20-11-25
 */
data class MineFmBean(
    val accumulatedComment: Int,
    val commentWall: List<CommentWall>,
    val reputationCore: Int,
    val soldingNum: Int,
    val soledNum: Int
) {
    data class CommentWall(
        val commentContent: String,
        val commentatorAvatarPic: String,
        val commentatorUid: Int
    )
}
