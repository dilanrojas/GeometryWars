package CRUD.modelo.dataset;

import CRUD.modelo.Usuario;

/**
 * @author Dilan Rojas
 * @date Nov 7, 2025
 * @version 1.0
 * @description description
 */

public class ListaUsuarios {
	// Atributos
	private Usuario[] lista;
	private int size;
	private int ultimoID;
	private final int TAMANO_INICIAL = 20;
	
	// === Constructores ===
	
	// Lista con el tamaño inicial
	public ListaUsuarios() {
		this.lista = new Usuario[TAMANO_INICIAL];
		this.size = 0;
		this.ultimoID = 0;
	}
	
	// Lista con tamaño personalizado
	public ListaUsuarios(int tamano) {
		this.lista = new Usuario[Math.max(tamano, 1)];
		this.size = 0;
		this.ultimoID = 0;
	}
	
	// === MÉTODOS ===
	
	// Obtener
	public Usuario getElemento(int index) {
		if (index < 0 || index >= size) return null;
		return lista[index];
	}
	
	// Agregar
	public boolean agregar(Usuario usuario) {
		if (usuario == null) return false;
		if (size >= lista.length) crecer();
		lista[size++] = usuario;
		usuario.setID(++ultimoID);
		return true;
	}
	
  // Crecer
  private void crecer() {
      int nuevoTamano = lista.length * 2;
      Usuario[] nuevaLista = new Usuario[nuevoTamano];
      for (int i = 0; i < lista.length; i++) {
          nuevaLista[i] = lista[i];
      }
      this.lista = nuevaLista;
  }

  // Crecer (sobrecarga)
  private Usuario[] crecer(Usuario[] arreglo) {
      int nuevoTamano = arreglo.length * 2;
      Usuario[] nuevaLista = new Usuario[nuevoTamano];
      for (int i = 0; i < arreglo.length; i++) {
          nuevaLista[i] = arreglo[i];
      }
      return nuevaLista;
  }

  // Imprimir
  public String imprimir() {
      if (size == 0 || lista == null) return null;
      String usuarios = "";
      for (Usuario usuario : lista) {
    	  if (usuario != null) usuarios += usuario.getID() + ". " + usuario.getNickname() + "\n";
      }
      return usuarios;
  }

  // Obtener size
  public int getSize() {
      return size;
  }

  // Obtener ultimoID
  public int getUltimoID() {
	  return ultimoID;
  }

  // Verificar si la lista está vacía
  public boolean isEmpty() {
      return size == 0;
  }

  // Limpiar la lista
  public void clear() {
	for (int i = 0; i < size; i++) lista[i] = null;
	size = 0;
  }

  // Corrimiento
  public void corrimiento(int index) {
	  for (int i = index; i < size - 1; i++) {
		  lista[i] = lista[i + 1];
	  }
	  lista[size - 1] = null;
	  size--;
  }

  // Eliminar
  public boolean eliminar(int index) {
	  if (index < 0 || index >= size) return false;
	  lista[index] = null;
	  corrimiento(index);
	  return true;
  }
  
  // Intercambiar
  public void intercambiar(int i, int j) {
	  Usuario listaI = lista[i];
	  lista[i] = lista[j];
	  lista[j] = listaI;
  }
  
  // Ordenamiento
  public Usuario[] ordenamiento(Usuario[] listaPorOrdenar, int sizeLista) {
	    // Crear copia para no modificar la lista real
	    Usuario[] listaOrdenada = new Usuario[sizeLista];
	    for (int i = 0; i < sizeLista; i++) {
	        listaOrdenada[i] = listaPorOrdenar[i];
	    }

	    // Ordena descendientemente según puntaje
	    for (int i = 0; i < sizeLista - 1; i++) {
	        for (int j = 0; j < sizeLista - 1; j++) {
	            if (listaOrdenada[j].getPuntaje() < listaOrdenada[j + 1].getPuntaje()) {
	                Usuario temp = listaOrdenada[j];
	                listaOrdenada[j] = listaOrdenada[j + 1];
	                listaOrdenada[j + 1] = temp;
	            }
	        }
	    }

	    return listaOrdenada;
	}

  
  // Records
  public String records(int cantidadRecords) {
	  Usuario[] listaCopiaOrdenada = ordenamiento(lista, size);
	  Usuario[] records = new Usuario[cantidadRecords];
	  String listaRecords = "Usuario   -   Puntaje\n" +
			  				"----------------------------\n";
	  
	  for (int i = 0; i < cantidadRecords; i++) {
		  records[i] = listaCopiaOrdenada[i];
	  }
	  
	  for (Usuario usuario : records) {
		  listaRecords += usuario.toString() + "  -  " + usuario.getPuntaje() + "\n";
	  }
	  
	  return listaRecords;
  }

  // === BÚSQUEDAS ===

  // Buscar por nombre, nickname, puntaje y nivel
  public Usuario[] buscar(String entrada) {
	  Usuario[] coincidencias = new Usuario[10];
	  int cantidadCoincidencias = 0;

	  // Comprobar si la entrada es un numero (ID o Nivel) o no.
	  try {
		  // Si es un numero, se busca por puntaje y Nivel.
		  int entradaNumero = Integer.parseInt(entrada);

		  for (Usuario usuario : lista) {
			  if (usuario != null && (usuario.getNivel() == entradaNumero || usuario.getPuntaje() == entradaNumero)) {
				  if (coincidencias.length >= cantidadCoincidencias) coincidencias = crecer(coincidencias);
				  coincidencias[cantidadCoincidencias++] = usuario;
			  }
		  }

	  } catch (NumberFormatException e) {
		  // No es un número, se busca solo por nombre y nickname
		  String entradaNormalizada = entrada.toLowerCase();

		  for (Usuario usuario: lista) {
			  if (usuario != null && (usuario.getNombre().toLowerCase().contains(entradaNormalizada) ||
				  usuario.getNickname().toLowerCase().contains(entradaNormalizada)) 
			  ) {
				  if (coincidencias.length >= cantidadCoincidencias) coincidencias = crecer(coincidencias);
				  coincidencias[cantidadCoincidencias++] = usuario;
			  }
		  }
	  }

	  // Devolver arreglo sin espacios vacíos
	  Usuario[] resultadosFinales = new Usuario[cantidadCoincidencias];
	  for (int i = 0; i < cantidadCoincidencias; i++) {
	      resultadosFinales[i] = coincidencias[i];
	  }

	  return resultadosFinales;
  }
}
