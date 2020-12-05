package pl.kielce.tu.swi_project.statistics;

import lombok.Getter;
import pl.kielce.tu.swi_project.model.VoivodeshipData;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ColumnStats {

    @Getter
    private int quantile33;

    @Getter
    private int quantile66;

    public ColumnStats(List<VoivodeshipData> voivodeshipData, Function<VoivodeshipData, Integer> dataExtractor) {
        List<Integer> data = voivodeshipData
                .stream()
                .map(dataExtractor)
                .sorted()
                .collect(Collectors.toList());
        this.quantile33 = data.get(data.size()/3);
        this.quantile66 = data.get(2*data.size()/3);
    }
}
