package org.wit.flowers.activities

import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.flowers.R
import java.util.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, AnkoLogger {

    lateinit var map: GoogleMap
    lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var lastLocation: Location

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val PLACE_PICKER_REQUEST = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.getUiSettings().setZoomControlsEnabled(true)
        map.mapType = GoogleMap.MAP_TYPE_TERRAIN
        val lismore = LatLng(52.1399046, -7.9328011)
        val cappoquin = LatLng(52.1494699, -7.8503232)
        val congreve = LatLng(52.2409053, -7.2189952)
        val tramore = LatLng(52.1633144, -7.1498740)

        placeMarkerOnMap(cappoquin, "Cappoquin House and Gardens")
        placeMarkerOnMap(lismore, "Lismore Castle Gardens")
        placeMarkerOnMap(congreve, "Mount Congreve Gardens")
        placeMarkerOnMap(tramore, "Lafcadio Hearn Japanese Gardens")

        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLng, "Current Location")
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 9f))
            }
        }
        setUpMap()

    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        map.isMyLocationEnabled = true

    }

    override fun onMarkerClick(p0: Marker?) = false


    private fun placeMarkerOnMap(location: LatLng, name: String ) {
        val title = getAddress(location, name)
        info("The address is $title")
        map.addMarker(MarkerOptions().title(title).position(location).draggable(true))
            .showInfoWindow()

    }

    private fun getAddress(location: LatLng, name: String): String {

        val addresses: List<Address>
        val geocoder = Geocoder(this, Locale.getDefault())

        addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)

        val address = addresses[0].getAddressLine(0)
        val string = "$name, $address"
        return string
    }
}

