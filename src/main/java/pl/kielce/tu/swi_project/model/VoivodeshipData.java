package pl.kielce.tu.swi_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoivodeshipData {
    private int id;

    private String name;

    private int accidentsCount;

    private int victims;

    private int injured;

    private int afterAlcoholUsage;

    private int drunkDrivers;
}
