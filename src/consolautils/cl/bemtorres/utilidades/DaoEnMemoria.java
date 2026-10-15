package consolautils.cl.bemtorres.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DaoEnMemoria<T extends Identificable<ID>, ID> {
    private final List<T> elementos = new ArrayList<>();

    public void guardar(T elemento) {
        Objects.requireNonNull(elemento, "El elemento no puede ser null.");
        ID id = validarId(elemento.getId());
        if (buscarPorId(id).isPresent()) {
            throw new IllegalArgumentException("Ya existe un elemento con ese ID: " + id);
        }
        elementos.add(elemento);
    }

    public Optional<T> buscarPorId(ID id) {
        ID idValidado = validarId(id);
        return elementos.stream()
                .filter(elemento -> Objects.equals(elemento.getId(), idValidado))
                .findFirst();
    }

    public void actualizar(T elemento) {
        Objects.requireNonNull(elemento, "El elemento no puede ser null.");
        ID id = validarId(elemento.getId());
        for (int indice = 0; indice < elementos.size(); indice++) {
            if (Objects.equals(elementos.get(indice).getId(), id)) {
                elementos.set(indice, elemento);
                return;
            }
        }
        throw new IllegalArgumentException("No existe un elemento con ese ID: " + id);
    }

    public boolean eliminar(ID id) {
        ID idValidado = validarId(id);
        return elementos.removeIf(elemento -> Objects.equals(elemento.getId(), idValidado));
    }

    public List<T> listar() {
        return List.copyOf(elementos);
    }

    private ID validarId(ID id) {
        return Objects.requireNonNull(id, "El ID no puede ser null.");
    }
}
