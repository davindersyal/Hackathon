package com.android.savery.ui.baseclass

import android.widget.Toast
import androidx.annotation.MainThread

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.android.savery.di.ViewModelFactory
import com.sdi.joyersmajorplatform.uiview.recyclerview.DataBoundAdapterClass
import com.sdi.joyersmajorplatform.uiview.recyclerview.DataBoundPagedListAdapter
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2


abstract class BaseDaggerActivity : DaggerAppCompatActivity() {


    /**
     * [ViewModelFactory] which uses Dagger2 for dependency injection
     */
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected fun <X : DataBoundAdapterClass<T, *>, T> initAdapter(
        adapter: X,
        recycler: RecyclerView,
        list: ArrayList<T>
    ): X {
        recycler.adapter = adapter
        adapter.submitList(list)

        return adapter
    }


    /*This is for viewPagerAdapter Adapter*/

    fun <X : DataBoundAdapterClass<T, *>, T> X.setup(
        viewPager: ViewPager2,
        data: List<T>
    ) {
        viewPager.adapter = this
        submitList(data)
    }


    protected fun <X : DataBoundPagedListAdapter<T, *>, T> initAdapter(
        adapter: X,
        recycler: RecyclerView,
        list: PagedList<T>
    ): X {
        recycler.adapter = adapter
        adapter.submitList(list)
//        recycler.addOnItemTouchListener(RecyclerTouchListener(this, recycler, { view, position ->
//            Log.e("infoamtion are", "" + position)
//        }))
        return adapter
    }

    protected fun <X : DataBoundAdapterClass<T, *>, T> initAdapter(
        adapter: X,
        recycler: RecyclerView,
        list: LiveData<List<T>>
    ): X {
        recycler.adapter = adapter
        list.observe(this, Observer {
            adapter.submitList(it)
        })

        return adapter
    }


    /**
     * Wrapper for [ComponentActivity.viewModels]
     * Uses the dagger viewModelFactory by default to avoid having to specify it each time.
     */
    @MainThread
    inline fun <reified VM : ViewModel> viewModels() = viewModels<VM>
    { viewModelFactory }


    /**
     * Subscribes to a [Observable] and handles disposing.
     */
    fun <T> subscribe(stream: Observable<T>?, handler: (T) -> Unit) {
        if (stream == null) return
        subscriptions += stream.subscribe(handler) {
            //  Timber.e(it)
        }
    }

    /**
     * Container for RxJava subscriptions.
     */
    private val subscriptions = CompositeDisposable()


    fun showMessage(message: String?) {
        message?.let {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }


    }


}
