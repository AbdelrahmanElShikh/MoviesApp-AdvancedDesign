package com.noha.moviesadvanced.core.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Actor(
    @SerializedName("name") @Expose  val name: String,
    @SerializedName("profile_path") @Expose   val photoUrl: String
)