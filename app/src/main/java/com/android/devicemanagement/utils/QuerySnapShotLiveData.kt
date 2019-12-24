package com.android.devicemanagement.utils

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.*

class QuerySnapshotLiveData(private val query: Query) : LiveData<Resource<QuerySnapshot>>(),
    EventListener<QuerySnapshot> {

    private var registration: ListenerRegistration? = null

    override fun onEvent(snapshots: QuerySnapshot?, e: FirebaseFirestoreException?) {
        Log.e("data are","event")
        value = if (e != null) {
            Resource(e)
        } else {
            Resource(snapshots!!)
        }




    }

    override fun onActive() {
        Log.e("data are","active")
        super.onActive()
        registration = query.addSnapshotListener(this)
    }

    override fun onInactive() {
        super.onInactive()
        Log.e("data are","inactive")
        registration?.also {
            it.remove()
            registration = null
        }
    }
}