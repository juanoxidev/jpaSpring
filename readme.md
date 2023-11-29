## HashMap
Proporciona la implementación de un mapa basado en tablas de dispersión (hash table). Un mapa es una estructura de datos que asocia claves con valores. En el caso de HashMap, las claves y los valores pueden ser de cualquier tipo de objeto.

Aquí tienes una explicación básica de cómo funciona la clase HashMap:

Almacenamiento basado en tablas de dispersión (hash table): HashMap utiliza una tabla de dispersión interna para almacenar los elementos. En lugar de almacenar directamente los elementos en índices, se utiliza una función hash para calcular la ubicación en la que se almacenará cada elemento.

Claves únicas: Las claves en un HashMap deben ser únicas. Si intentas agregar una clave que ya existe en el mapa, el valor asociado con esa clave se actualizará.

Función hash: Cuando se inserta un par clave-valor, la clase HashMap utiliza la función hash de la clave para determinar en qué posición de la tabla de dispersión se almacenará el par. La función hash convierte la clave en un valor entero, y ese valor se utiliza para calcular la posición en la tabla.

Manejo de colisiones: Puede haber casos en los que dos claves diferentes produzcan el mismo valor hash, lo que lleva a una colisión. Para manejar esto, HashMap utiliza listas vinculadas en cada posición de la tabla de dispersión. Si hay una colisión, los elementos se agregan a la lista vinculada en esa posición.

Tamaño dinámico: HashMap ajusta dinámicamente su tamaño interno a medida que se agregan o eliminan elementos. Esto ayuda a garantizar un buen rendimiento incluso cuando la cantidad de elementos en el mapa cambia.