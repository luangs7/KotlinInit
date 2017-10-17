package br.com.squarebits.kotlininit.model

import com.google.gson.annotations.SerializedName

data class Installation(

	@field:SerializedName("cash_mode")
	val cashMode: String? = null,

	@field:SerializedName("restock_mode")
	val restockMode: String? = null,

	@field:SerializedName("location")
	val location: Location? = null,

	@field:SerializedName("machine_id")
	val machineId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("place")
	val place: String? = null,

	@field:SerializedName("notifications_enabled")
	val notificationsEnabled: Boolean? = null,

	@field:SerializedName("equipment_id")
	val equipmentId: Int? = null
)