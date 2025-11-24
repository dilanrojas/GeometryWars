package CRUD.modelo.dao.archivoJSON;

/**
 * @author Ana Gonzalez C5F593
 * @date Nov 7, 2025
 * @version 1.0
 * @description Clase escritor 
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

import CRUD.modelo.Configuraciones;
import CRUD.modelo.Usuario;
import CRUD.modelo.dataset.ListaUsuarios;

public class EscritorJSON {
	// Componentes
	private ListaUsuarios dataset;
	private final String fileName;

	// Constructor
	public EscritorJSON(ListaUsuarios dataset, String fileName) {
		this.dataset = dataset;
		this.fileName = fileName;
	}

	// Escibir en .json
	public void writeAll() throws IOException {
		File file = new File(fileName);

		if (file.getParentFile() == null) {
			file.getParentFile().mkdirs();
		}

		JSONArray jsonArray = new JSONArray();

		for (int i = 0; i < dataset.getSize(); i++) {
			Usuario usuario = dataset.getElemento(i);
			if (usuario != null) {
				JSONObject elemento_json = new JSONObject();
				
				if (usuario.getConfiguraciones() != null) {

					Configuraciones configuraciones = usuario.getConfiguraciones();

					JSONObject configs_json = new JSONObject();
					configs_json.put("velocidad", configuraciones.getVelocidad());
					configs_json.put("arma", configuraciones.getArma());
					configs_json.put("dificultad", configuraciones.getDificultad());
					configs_json.put("vidas", configuraciones.getVidas());
					elemento_json.put("configuraciones", configs_json);
				}
				
				elemento_json.put("nombre", usuario.getNombre());
				elemento_json.put("nickname", usuario.getNickname());
				elemento_json.put("contrasena", usuario.getContrasena());
				elemento_json.put("nivel", usuario.getNivel());
				elemento_json.put("puntaje", usuario.getPuntaje());
				elemento_json.put("ID", usuario.getID());
				elemento_json.put("tiempoJugado", usuario.getTiempoJugado());

				jsonArray.put(elemento_json);
			}
		}

		JSONObject root = new JSONObject();
		root.put("usuarios", jsonArray);

		try (FileOutputStream output = new FileOutputStream(file)) {
			output.write(root.toString(4).getBytes(StandardCharsets.UTF_8));
			System.out.println("Archivo JSON guardado en: " + file.getAbsolutePath());
		} catch (IOException e) {
			System.err.println("Error al guardar JSON: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
