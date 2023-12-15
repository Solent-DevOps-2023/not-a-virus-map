package com.example.harryMaynard

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.location.LocationListener
import android.location.Location
import android.os.Bundle
import androidx.preference.PreferenceManager
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.OverlayItem
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity(), LocationListener {

    private var locationManager: LocationManager? = null

    //Handles preferances
    private val prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)

    //Holds the database DAO
    private val db = LocationDatabase.getDatabase(application)

    //Launcher for menu to input data for a marker
    private val optionsLauncher=
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            it.data?.apply {
                //Gets map so the map center we cna get the map center
                val map1 = findViewById<MapView>(R.id.map1)
                //If data provided by the user then it will be set as Empty name or Empty description
                addMarker(this.getStringExtra("Name") ?: "Empty name",this.getStringExtra("Description") ?: "Empty description",this.getStringExtra("Type") ?: "Empty type", map1.mapCenter.latitude,map1.mapCenter.longitude)

                saveMarker(this.getStringExtra("Name") ?: "Empty name",this.getStringExtra("Description") ?: "Empty description",this.getStringExtra("Type") ?: "Empty type", map1.mapCenter.latitude,map1.mapCenter.longitude)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))

        setContentView(R.layout.activity_main)

        //Set up POI add button
        val addMarkerButton = findViewById<Button>(R.id.button)
        addMarkerButton.setOnClickListener() {
            val intent = Intent(this,MapChooseActivity::class.java)
            optionsLauncher.launch(intent)
        }


        //Checks if permissions are granted and if not passes the needed permissions to the requestPermissions function
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ||  ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE), 0)
        }

        //Using location manager so location can update as user moves
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,this)
        val location = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        //Sets up map
        val map1 = findViewById<MapView>(R.id.map1)
        map1.controller.setZoom(14.0)

        //Get latitude and longitude in null safe way
        val latitude = location?.latitude ?: 0.0
        val longitude = location?.longitude ?: 0.0

        //Sets initial location
        map1.controller.setCenter(GeoPoint(latitude, longitude))

    }

    //Gets array of permissions and asks the user to grant each one
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            0 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    return
                } else {
                    AlertDialog.Builder(this)
                        .setPositiveButton("Okay", null)
                        .setMessage("You have denied a critical permission, the app may be unstable")
                        .show()
                }
            }
        }
    }

    //Gets marker data from parameters and adds marker to map
    private fun addMarker(name:String, description:String, type:String, lat:Double, lon: Double){
        val map = findViewById<MapView>(R.id.map1)
        val items = ItemizedIconOverlay(this, arrayListOf<OverlayItem>(), null)
        val marker= OverlayItem(name, description, GeoPoint(lat, lon))
        items.addItem(marker)
        map.overlays.add(items)
    }

    //Takes in marker values and saves the result in the loations database
    private fun saveMarker(name:String, description:String, type:String, lat:Double, lon: Double){
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                db.LocationDAO().insert(LocationPOI(id=0,name=name,description=description,type=type,lon=lon,lat=lat));
            }
        }
    }

    private fun loadMarkers(){
        lifecycleScope.launch {
            var markers :List<LocationPOI>? = null
            withContext(Dispatchers.IO) {
                markers=db.LocationDAO().getAllLocations()
            }
            if(markers!=null) {
                for (marker in markers!!) {
                    addMarker(marker.name,marker.description,marker.type,marker.lat,marker.lon)
                }
            }
        }
    }

    //Runs code when location changes
    override fun onLocationChanged(newLoc: Location) {
        val map1 = findViewById<MapView>(R.id.map1)

        map1.controller.setCenter(GeoPoint(newLoc.latitude, newLoc.longitude))
    }

    //Menu functions only really needed in this view as the second one is used for data entry
    //Inflates Menu
    override fun onCreateOptionsMenu(menu: Menu) : Boolean {
        menuInflater.inflate(R.menu.options, menu)
        return true
    }
    //Creates menu where saving to disk can be selected
    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        when(item.itemId) {
            R.id.options-> {
                val intent = Intent(this,Preferences::class.java)
                startActivity(intent)
            }
        }
        return false
    }

    //When the user returns from setting preferences this will update them for the activity
    override fun onResume() {
        super.onResume()

        //Gets current save to disk preferences
        val saveToDisk= prefs.getBoolean("saveToDisk", true) ?: true
    }
}