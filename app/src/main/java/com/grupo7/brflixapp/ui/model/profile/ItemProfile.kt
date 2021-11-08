package com.grupo7.brflixapp.ui.model.profile

import com.grupo7.brflixapp.util.enumarators.ProfileItemActionEnum

class ItemProfile(val itemTitle: String, val itemDescription: String?, val isClickable: Boolean, val action: ProfileItemActionEnum) {
}