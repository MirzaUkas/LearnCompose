package mirz.study.learncompose

import androidx.annotation.DrawableRes

data class UserProfile(
    val name: String,
    val status: Boolean,
    @DrawableRes val drawableId: Int
)

val users = arrayListOf(
    UserProfile("Toni", true, R.drawable.profile_picture),
    UserProfile("Susi", false, R.drawable.profile_picture_2),
)
