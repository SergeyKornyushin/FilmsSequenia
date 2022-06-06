package com.example.filmssequenia.kotlinapp.ui.activities.base

///**
// * Базовая Activity c AppBar и навигацией
// */
//abstract class BaseWithAppBarNavigationActivity(
//    @LayoutRes contentLayoutId: Int = R.layout.base_with_app_bar_activity
//) : BaseNavigationActivity(contentLayoutId), AppBarProvider,
//    AppBarViews {
//
//    private lateinit var appBarProvider: AppBarProviderImp
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        initAppBar()
//    }
//
//    override fun getAppBarProviderImp(): AppBarProviderImp = appBarProvider
//
//    override fun getCollapsingContent(): ViewGroup = findViewById(R.id.collapsingView)
//
//    override fun getAppBar(): AppBarLayout = findViewById(R.id.appBar)
//
//    override fun getToolbar(): Toolbar = findViewById(R.id.toolbar)
//
//    override fun getCollapsingToolbarLayout(): CollapsingToolbarLayout =
//        findViewById(R.id.collapsingToolbarLayout)
//
//    private fun initAppBar() {
//        appBarProvider = AppBarProviderImp(this)
//        setSupportActionBar(toolbar)
//    }
//}