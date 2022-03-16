package com.retrofit.model

data class Listings(var make: String? = null,var listPrice:Int=0,val images: Images,
                    var dealer:Dealer,
                    var year: String? = null,var trim: String? = null,var model: String? = null,
                    var mileage: String? = null,
                    var drivetype: String? = null,
                    var interiorColor: String? = null,
                    var transmission: String? = null,
                    var engine: String? = null,
                    var fuel: String? = null,
                    var exteriorColor: String? = null)