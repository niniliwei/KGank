package com.niniliwei.kgank.api

data class ResponseBean(val _id: String, val createdAt: String, val desc: String,
                        val images: List<String>, val publishedAt: String,
                        val source: String, val type: String, val url: String,
                        val used: Boolean, val who: String)

data class BaseResp(val error: Boolean, val results: List<ResponseBean>)