### Composition root with delegation

This is in my opinion the solution that we should explore more and try to adopt. It is kind of inversion of control but with navigation components.

Basically, every screen will declare a delegate, containing a contract of events that the composition root layer will listen to navigate to other screens.

```
interface IRestaurantCatalogDelegate {  
    fun checkoutButtonClick(args: CheckoutArgs)  
  
    fun itemDetailsClick()  
}  
  
class RestaurantCatalogFragment : Fragment {
	private val delegate by inject<IRestaurantCatalogDelegate> { parametersOf(activity) }
// ...
} 
```

With this contract, we can avoid the responsibility of the view to know where she needs to go, or any details about other features. Since all she has to do is call a method and someone else will take care of the navigation or any related logic. This delegate can also be implemented using an event base with sealed classes, like:

```
sealed class RestaurantCatalogEvents {  
    data class CheckoutClick(args: CheckoutArgs) : RestaurantCatalogEvents()  
    object DetailsClick : RestaurantCatalogEvents()  
}
```

And the delegate will have a single method receiving events as parameters, depending on the amount of events this approach can be a problem, having a when with numerous cases. And with the one method per event, we can use ISP to split the interface in several ones if it grows.

Another benefit of this approach is, if your project has a demo application (a module where you can run the feature in isolation), you can change the behaviour of your navigation for test purposes without the need to change any code inside your feature module

On the composition root, all we have to do is:

```
class RestaurantCatalogDelegate(private val activity: AppCompatActivity) :  
    INavigatorProvider by DefaultNavigatorProvider(  
        activity  
    ), IRestaurantCatalogDelegate {  
  
    override fun checkoutClick(args: CheckoutArgs) {  
        controller.navigate(checkout.id.checkout_navigation, bundleOf("args" to args))  
    }  
  
    override fun itemDetailsClick() {  
        controller.navigate(restaurant.id.itemDetailFragment)  
    }  
}
```

#### Navigation arguments

Here we have an attention point which is the CheckoutArgs object being used inside the Restaurant feature, coupling one feature to another, this object exists inside the public module of the checkout feature.

Something that we can do to avoid this problem would be, to create some sort of mapper, which will be executed on the composition root before navigating to the checkout screen, for example. So we will have something like:

```

// Restaurant module
data class RestaurantCatalogDTO(val items: Int)

// Composition root
...
override fun checkoutButtonClick(args: RestaurantCatalogDTO) {
	val checkoutArgs = RestaurantMapper.dtoToCheckoutArgs(args)
	controller.navigate(checkout.id.checkout_navigation, bundleOf("args" to checkoutArgs))  
}
...
```

Doing this, we can avoid having the public dependency of the checkout inside the restaurant feature module.

The question is, having the args from checkout inside the restaurant is bad for the project / architecture? And, should we do the parsing on the implementation of the delegate or should we find a more suitable layer for that before the navigation.

We still have another option which is to stop using complex objects to navigate between screens, and use language types, like restaurant ID (String), item ID (Int) ... This way we can have a better support for deep links, since normally we have only the ID of something to load the screen.

But, this imposes some problems like:

1.  Increase the load on the API to retrieve objects;
2.  Having some sort of local cache, so you can retrieve it between screens, and deal with cache policies ...

#### Compose

In case that you want to use jetpack compose with the composition root approach, we can easily create an activity that supports compose layouts or, in existing projects, just create a fragment, which returns a composable function for the view.

```
...

override fun onCreateView(  
    inflater: LayoutInflater,  
    container: ViewGroup?,  
    savedInstanceState: Bundle?  
) = setContent {  
    BuildOrderTrackerScreen()  
}  
  
@Preview  
@Composable  
fun BuildOrderTrackerScreen() {  
    Column(  
        modifier = Modifier  
            .fillMaxSize(),  
        Arrangement.Center,  
        Alignment.CenterHorizontally,  
    ) {  
        Text("Hello from Compose")  
    }  
}
```

And all the rest remains the same, create the jetpack navigation graph and include this fragment there, and all we have to do to navigate to it is call the navigation like we did on other examples.

If you decide that the entire feature will be built using compose, I believe that we can use this fragment as a backbone for the whole feature, no need to create a new fragment per screen, since compose does not need fragments.

Unfortunately, compose handle the navigation differently, so the navigation within composable screens will be a topic for another study case.