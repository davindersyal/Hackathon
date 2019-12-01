package com.android.savery.ui.baseclass

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.sdi.joyersmajorplatform.uiview.recyclerview.DataBoundAdapterClass
import com.sdi.joyersmajorplatform.uiview.recyclerview.DataBoundPagedListAdapter

/**
 * [BaseDaggerFragment] that adds helper methods for displaying lists.
 */
abstract class BaseDaggerListFragment<TBinding : ViewDataBinding> : BaseDaggerFragment<TBinding>(){

    protected fun <X : DataBoundAdapterClass<T, *>, T> initAdapter(
        adapter: X,
        recycler: RecyclerView,
        list: ArrayList<T>
    ): X {
        recycler.adapter = adapter
        adapter.submitList(list)
        return adapter
    }


    protected fun <X : DataBoundAdapterClass<T, *>, T> initAdapter(
        adapter: X,
        recycler: RecyclerView,
        list: MutableLiveData<ArrayList<T>>
    ): X {
        recycler.adapter = adapter
        list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        return adapter
    }



    protected fun <X : DataBoundAdapterClass<T, *>, T> initAdapter(
        adapter: X,
        recycler: RecyclerView,
        list: LiveData<ArrayList<T>>
    ): X {
        recycler.adapter = adapter
        list.observe(viewLifecycleOwner, Observer {
          //  adapter.submitList(it)
        })
        return adapter
    }





    protected fun <X : DataBoundPagedListAdapter<T, *>, T> initAdapter(
        adapter: X,
        recycler: RecyclerView,
        list: PagedList<T>
    ): X {
        recycler.adapter = adapter
        adapter.submitList(list)
        return adapter
    }

    


}