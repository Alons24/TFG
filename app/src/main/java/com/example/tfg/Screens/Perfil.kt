package com.example.tfg.Screens


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
/*
fun Perfil(navController: NavHostController){

    val scaffoldState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)


    /*Inicio del cajón lateral*/
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    //BOTÓN PARA BOLVER AL MENÚ DE INICIO
                    // Otros elementos del menú lateral
                    Button(
                        onClick = { navController.navigate(AppScreens.DejarResena.ruta) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(300.dp)
                            .height(100.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Color(4, 104, 249, 255))
                    ) {
                        Text(
                            text = "EJEMPLO",
                            fontSize = 25.sp,
                        )
                    }

                    //FIN DE LOS BOTONES DEL MENÚ LATERAL
                }
            }
        },
    ) {

        //Fin del cajón lateral y enpieza el Scaffold
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Blue, // Cambia el color de fondo
                        titleContentColor = Color.White, // Cambia el color del título
                    ),
                    title = {
                        Text("PERFIL")
                    },
                    /*navigationIcon = {
                        IconButton(onClick = { navController.navigate("MenuPrimero") }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description",
                                tint= Color.White
                            )
                        }
                    },
                    */

                    actions = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu",
                                tint= Color.White
                            )
                        }
                    }
                )
            },

        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Image(
                    painter = painterResource(R.drawable.clienteicono),
                    contentDescription = "fotoPerfil",
                    modifier = Modifier
                        .padding(vertical = 22.dp)
                        .height(200.dp)
                        .width(200.dp)
                )

                var time by remember {
                    mutableStateOf(0L)
                }
                var isRunning by remember{
                    mutableStateOf(false)
                }
                var startTime by remember{
                    mutableStateOf(0L)
                }
                val context = LocalContext.current

                val keyboardController= LocalSoftwareKeyboardController.current

                Column (modifier= Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                ){

                    Text(text = formatTime(timeMi=time),
                        style= MaterialTheme.typography.headlineLarge,
                        modifier=Modifier.padding(9.dp)
                    )
                    Spacer(modifier = Modifer.height(18.dp))
                    Row{
                        Button(onClick = {
                            if(isRunning) ) {
                            isRunning=false


                        }else{
                            startTime=System.currentTimeMillis() - time
                            isRunning=true
                            keyboardController?.h
                        }
                        }
                    }
                }
            }

            @Composable
            fun formatTime(timeMi : Long): String{

                val hours = TimeUnit.MILLISECONDS.toHours(timeMi)
                val min= TimeUnit.MILLISECONDS.toMinutes(timeMi) %60
                val sec =TimeUnit.MILLISECONDS.toSeconds(timeMi)%60

                return String.format("%02d:%02d:%02d", hours, min, sec)
            }
        }
    }


}
*/
