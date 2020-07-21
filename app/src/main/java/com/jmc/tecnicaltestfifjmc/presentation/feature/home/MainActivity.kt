package com.jmc.tecnicaltestfifjmc.presentation.feature.home

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.jmc.tecnicaltestfifjmc.App.Companion.context
import com.jmc.tecnicaltestfifjmc.R
import com.jmc.tecnicaltestfifjmc.domain.model.CurrenciesModel
import com.jmc.tecnicaltestfifjmc.domain.model.ItemCurrenciesModel
import com.jmc.tecnicaltestfifjmc.presentation.feature.detail.DetailActivity
import com.jmc.tecnicaltestfifjmc.utils.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

import com.jmc.tecnicaltestfifjmc.utils.courrutinas.Result
import com.jmc.tecnicaltestfifjmc.utils.isNetworkAvailable
import com.jmc.tecnicaltestfifjmc.utils.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity(), CurrencyManager {
    private val connectionManager: ConnectivityManager by inject()
    private val viewModel: HomeViewModel by viewModel()
    private val adapter1 = CurrencyAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.show()


        val item = intent.getStringExtra("user")


        toolbar.title = "Bienvenido ${item}"
        with(rvCurrencies) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = adapter1

            setHasFixedSize(true)
        }

        viewModel.getCurrencys()
        with(viewModel) {
            observe(lista, ::getListCurrency)
        }
    }


    private fun getListCurrency(result: Result<CurrenciesModel>?) {
        when (result) {
            is Result.OnLoading -> {
                pBarHome.visibility = View.VISIBLE
            }
            is Result.OnSuccess -> {
                pBarHome.visibility = View.GONE
                val artists = result.value.currencies
                adapter1.swapItems(new = artists)
            }
            is Result.OnError -> {
                pBarHome.visibility = View.GONE

                if (connectionManager.isNetworkAvailable())
                    Toast.makeText(context, "conexion fallida al servidor", Toast.LENGTH_LONG)
                else
                    Toast.makeText(
                        context,
                        "error de conexion, revisa tu internet e intenta nuevamente",
                        Toast.LENGTH_LONG
                    )
            }
            else -> {
                pBarHome.visibility = View.GONE
            }
        }
    }


    override fun onCurrencyClicked(item: ItemCurrenciesModel, position: Int) {
        startActivity<DetailActivity> {
            putExtra("itemCurrency", item)
        }
    }

//    private fun getListCurrency(search: SearchView) {
//        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                viewModel.getCurrencys()
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//
//                return true
//            }
//        })
//
//    }
}