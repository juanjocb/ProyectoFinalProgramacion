    package co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo;

    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;

    import java.io.Serializable;
    import java.util.ArrayList;

    @Data
    @AllArgsConstructor
    @Builder
    public class Admin implements Serializable {

        private static transient Admin instancia; // Singleton
        private String usuario;
        private String contrasenia;
        private ArrayList<Destino> destinos;
        private ArrayList<PaqueteTuristico> paquetesTuristicos;
        private ArrayList<GuiaTuristico> guiasTuristicos;

        private Admin() {
            destinos = new ArrayList<>();
            paquetesTuristicos = new ArrayList<>();
            guiasTuristicos = new ArrayList<>();
        }

        public static Admin obtenerInstancia() {
            if (instancia == null) {
                instancia = new Admin();
            }
            return instancia;
        }

        public void agregarDestino(Destino destino) {
            try {
                if (!destinos.contains(destino)) {
                    destinos.add(destino);
                }
            } catch (Exception e) {
                manejarError("Error al agregar destino: " + e.getMessage());
            }
        }

        public void eliminarDestino(Destino destino) {
            try {
                if (destinos.contains(destino)) {
                    destinos.remove(destino);
                } else {
                    throw new RuntimeException("No existe el destino");
                }
            } catch (Exception e) {
                manejarError(e.getMessage());
            }
        }

        public void actualizarDestino(Destino destinoActualizado) {
            try {
                for (Destino destino : destinos) {
                    if (destino.getNombre().equals(destinoActualizado.getNombre())) {
                        destino.setCiudad(destinoActualizado.getCiudad());
                        destino.setDescripcion(destinoActualizado.getDescripcion());
                        destino.setImagenes(destinoActualizado.getImagenes());
                        destino.setClima(destinoActualizado.getClima());
                        break;
                    }
                }
            } catch (Exception e) {
                manejarError("Error al actualizar destino: " + e.getMessage());
            }
        }

        public Destino buscarDestino(String nombre) {
            try {
                for (Destino destino : destinos) {
                    if (destino.getNombre().equals(nombre)) {
                        return destino;
                    }
                }
                return null;
            } catch (Exception e) {
                manejarError("Error al buscar destino: " + e.getMessage());
                return null;
            }
        }

        // Métodos similares para la gestión de guías y paquetes

        private void manejarError(String mensaje) {
            System.err.println("Error: " + mensaje);
        }


    // ... (Código anterior)

        public void agregarGuia(GuiaTuristico guiaTuristico) {
            try {
                if (!guiasTuristicos.contains(guiaTuristico)) {
                    guiasTuristicos.add(guiaTuristico);
                }
            } catch (Exception e) {
                manejarError("Error al agregar guía turística: " + e.getMessage());
            }
        }

        public void eliminarGuia(GuiaTuristico guiaTuristico) {
            try {
                if (guiasTuristicos.contains(guiaTuristico)) {
                    guiasTuristicos.remove(guiaTuristico);
                } else {
                    throw new RuntimeException("No existe el guía turístico");
                }
            } catch (Exception e) {
                manejarError(e.getMessage());
            }
        }

        public void actualizarGuia(GuiaTuristico guiaActualizado) {
            try {
                for (GuiaTuristico guiaTuristico : guiasTuristicos) {
                    if (guiaTuristico.getIdentificacion().equals(guiaActualizado.getIdentificacion())) {
                        guiaTuristico.setNombre(guiaActualizado.getNombre());
                        guiaTuristico.setLenguajes(guiaActualizado.getLenguajes());
                        guiaTuristico.setExperienciaAnios(guiaActualizado.getExperienciaAnios());
                        break;
                    }
                }
            } catch (Exception e) {
                manejarError("Error al actualizar guía turística: " + e.getMessage());
            }
        }

        public GuiaTuristico buscarGuia(String identificacion) {
            try {
                for (GuiaTuristico guiaTuristico : guiasTuristicos) {
                    if (guiaTuristico.getIdentificacion().equals(identificacion)) {
                        return guiaTuristico;
                    }
                }
                return null;
            } catch (Exception e) {
                manejarError("Error al buscar guía turística: " + e.getMessage());
                return null;
            }
        }

        public void agregarPaquete(PaqueteTuristico paquete) {
            try {
                if (!paquetesTuristicos.contains(paquete)) {
                    paquetesTuristicos.add(paquete);
                }
            } catch (Exception e) {
                manejarError("Error al agregar paquete turístico: " + e.getMessage());
            }
        }

        public boolean borrarPaquete(String nombrePaquete) {
            for (PaqueteTuristico paquete : paquetesTuristicos) {
                if (paquete.getNombre().equals(nombrePaquete)) {
                    paquetesTuristicos.remove(paquete);
                    return true;
                }
            }
            return false;
        }

        public void actualizarPaquete(PaqueteTuristico paqueteActualizado) {
            try {
                for (PaqueteTuristico paqueteTuristico : paquetesTuristicos) {
                    if (paqueteTuristico.getNombre().equals(paqueteActualizado.getNombre())) {
                        paqueteTuristico.setDestino(paqueteActualizado.getDestino());
                        paqueteTuristico.setDuracion(paqueteActualizado.getDuracion());
                        paqueteTuristico.setServiciosAdi(paqueteActualizado.getServiciosAdi());
                        paqueteTuristico.setPrecio(paqueteActualizado.getPrecio());
                        paqueteTuristico.setFechaInicio(paqueteActualizado.getFechaInicio());
                        paqueteTuristico.setFechaFinal(paqueteActualizado.getFechaFinal());
                        paqueteTuristico.setCupoMaximo(paqueteActualizado.getCupoMaximo());
                        break;
                    }
                }
            } catch (Exception e) {
                manejarError("Error al actualizar paquete turístico: " + e.getMessage());
            }
        }

        public PaqueteTuristico buscarPaquete(String nombre) {
            try {
                for (PaqueteTuristico paqueteTuristico : paquetesTuristicos) {
                    if (paqueteTuristico.getNombre().equals(nombre)) {
                        return paqueteTuristico;
                    }
                }
                return null;
            } catch (Exception e) {
                manejarError("Error al buscar paquete turístico: " + e.getMessage());
                return null;
            }
        }
    }
