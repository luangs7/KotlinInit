package br.com.squarebits.kotlininit.model

import com.google.gson.annotations.SerializedName

data class Location(

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("client_id")
	val clientId: Int? = null,

	@field:SerializedName("zip_code")
	val zipCode: String? = null,

	@field:SerializedName("number")
	val number: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("street")
	val street: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("neighborhood")
	val neighborhood: String? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("complement")
	val complement: Any? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null
)