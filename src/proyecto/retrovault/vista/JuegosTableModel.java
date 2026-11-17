package proyecto.retrovault.vista;

import proyecto.retrovault.modelo.Juego;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class JuegosTableModel extends AbstractTableModel {

    private static final String[] COLS
            = {"#", "Titulo", "Consola", "Genero", "Año", "Puntuacion", "Estado"};

    private List<Juego> juegos;

    public JuegosTableModel(List<Juego> juegos) {
        this.juegos = juegos;
    }

    public void setJuegos(List<Juego> juegos) {
        this.juegos = juegos;
        fireTableDataChanged();
    }

    public Juego getJuego(int fila) {
        return juegos.get(fila);
    }

    @Override
    public int getRowCount() {
        return juegos.size();
    }

    @Override
    public int getColumnCount() {
        return COLS.length;
    }

    @Override
    public String getColumnName(int col) {
        return COLS[col];
    }

    @Override
    public boolean isCellEditable(int r, int c) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Juego j = juegos.get(row);
        switch (col) {
            case 0:
                return row + 1;
            case 1:
                return j.getTitulo();
            case 2:
                return j.getConsola();
            case 3:
                return j.getGenero();
            case 4:
                return j.getAnio();
            case 5:
                if (j.getPuntuacion() == 0) {
                    return "-";
                } else {
                    return "*".repeat(j.getPuntuacion()) + " (" + j.getPuntuacion() + "/5)";
                }
            case 6:
                return j.getEstado();
            default:
                return "";
        }
    }
}
