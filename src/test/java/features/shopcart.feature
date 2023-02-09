Feature: Carrito de compras
  Como usuario quiero utilizar el carrito de compras para añadir o quitar productos.
  Scenario: Añadir un producto al carrito de compras
    Given El usuario ha iniciado sesión correctamente
    And El usuario se encuentra en la pantalla Inventario
    When El usuario añade un producto al carrito de compras
    Then El usuario visualiza que el producto se añadio al carrito de compras

  Scenario: Quitar un producto del carrito de compras
    Given El usuario ha iniciado sesión correctamente
    And El usuario se encuentra en la pantalla Inventario
    And El usuario añade un producto al carrito de compras
    When El usuario remueve el producto del carrito de compras
    Then El usuario visualiza que el producto se removió del carrito de compras