package com.testapp.model_classes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CountriesData {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("alpha2Code")
    @Expose
    var alpha2Code: String? = null

    @SerializedName("alpha3Code")
    @Expose
    var alpha3Code: String? = null

    @SerializedName("capital")
    @Expose
    var capital: String? = null

    @SerializedName("region")
    @Expose
    var region: String? = null

    @SerializedName("subregion")
    @Expose
    var subregion: String? = null

    @SerializedName("population")
    @Expose
    var population: Int? = null

    @SerializedName("demonym")
    @Expose
    var demonym: String? = null

    @SerializedName("area")
    @Expose
    var area: Float? = null

    @SerializedName("gini")
    @Expose
    var gini: Float? = null

    @SerializedName("nativeName")
    @Expose
    var nativeName: String? = null

    @SerializedName("numericCode")
    @Expose
    var numericCode: String? = null

}