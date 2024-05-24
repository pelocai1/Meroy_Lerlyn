
package pedidosMeroy_Lerlin;


public class Producto {
    private String codigoProducto;
    private String nombre;
    private String gama;
    private String dimensiones;
    private String proveedor;
    private String descripcion;
    private int stock;
    private float precio;
    private float precioProveedor;

    public Producto() {
    }

    public Producto(String codigoProducto, String nombre, String gama, String dimensiones, String proveedor, String descripcion, int stock, float precio, float precioProveedor) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.gama = gama;
        this.dimensiones = dimensiones;
        this.proveedor = proveedor;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.precioProveedor = precioProveedor;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGama() {
        return gama;
    }

    public void setGama(String gama) {
        this.gama = gama;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getPrecioProveedor() {
        return precioProveedor;
    }

    public void setPrecioProveedor(float precioProveedor) {
        this.precioProveedor = precioProveedor;
    }
    
}
