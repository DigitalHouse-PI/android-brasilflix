package com.grupo7.brflixapp.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.grupo7.brflixapp.data.api.util.Command

abstract class BaseFragment: Fragment() {

    abstract var command: MutableLiveData<Command>
}