package br.com.squarebits.kotlininit.model

import com.google.gson.annotations.SerializedName

data class Machine(

	@field:SerializedName("machine_model_id")
	val machineModelId: Int? = null,

	@field:SerializedName("asset_number")
	val assetNumber: String? = null,

	@field:SerializedName("installation")
	val installation: Installation? = null,

	@field:SerializedName("external_id")
	val externalId: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null
)