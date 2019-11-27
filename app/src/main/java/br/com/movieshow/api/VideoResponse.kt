package br.com.movieshow.api

import br.com.movieshow.models.Videos
import com.google.gson.annotations.SerializedName

data class VideoResponse (
    @SerializedName("results")
    var videos: List<Videos>
)