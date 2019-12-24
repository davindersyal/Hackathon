package com.android.devicemanagement.ui.baseclass

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.sdi.joyersmajorplatform.uiview.recyclerview.DataBoundAdapterClass
import com.sdi.joyersmajorplatform.uiview.recyclerview.DataBoundPagedListAdapter
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

/**
 * [BaseDaggerFragment] that adds helper methods for displaying lists.
 */
abstract class BaseDialogDaggerListFragment<TBinding : ViewDataBinding>:DialogFragment(){

    /**
     * Container for RxJava subscriptions.
     */
    private val subscriptions = CompositeDisposable()

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


    /**
     * Subscribes to a [Observable] and handles disposing.
     */
    fun <T> subscribe(stream: Observable<T>?, handler: (T) -> Unit) {
        if (stream == null) return
        subscriptions += stream.subscribe(handler) {
            //  Timber.e(it)
        }
    }




}