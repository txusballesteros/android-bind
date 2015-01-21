# Android Binding Library

This library helps to you on process related to data binds between your model entities and your application UI, offering a bidirectional data channel.

## Version

1.0.0

## How to use

```java
class MyModel {

    @BindTo(viewId = R.id.name)
    private String name = "Txus Ballesteros";
    
    ...
}
```
Use @BindTo annotation on your model class to configure binding between your model field and your UI View.

```java
public class MainActivity extends Activity {
     MyModel myModel;
     ViewGroup rootView;
     Binder myModelBinder;
    
     @Override
     protected void onCreate(Bundle savedInstanceState) {
        ...
        
        myModelBinder = new Binder.Builder()
                                .setSource(myModel)
                                .setDestination(rootView)
                                .build();
                                
        ...
     }
     
     @Override
     protected void onResume() {
        ...
        
        //Use this code to init the data binding process. 
        //  In this case execute the binding between your Model Class and your app UI.
        myModelBinder.bind();
        
        //Use this code to init the reversed data binding process.
        //  In this case execute the binding between your app UI and your Model Class.
        myModelBinder.bindReverse();
     }
    ...
}
```
IMPORTANT!! Look the methods setSource and setDestination of the Binder.Builder, these methods establish the data binding direction. For example, 
if you set at source your Model class and set into Destination you ViewGroup, when you call to the bind method, this will be the natural direction of 
the data. And you will need to call bindReverse method to invert the direction of the data.


## License

Copyright 2015 Txus Ballesteros

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

