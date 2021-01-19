package pl.kielce.tu.swi_project.statistics;

import lombok.Getter;
import pl.kielce.tu.swi_project.model.VoivodeshipData;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ColumnStats {

    @Getter
    private int min = Integer.MAX_VALUE;

    @Getter
    private int quantile33;

    @Getter
    private int median;

    @Getter
    private int quantile66;

    @Getter
    private int max = 0;

    @Getter
    private double average;

    public ColumnStats(List<VoivodeshipData> voivodeshipData, Function<VoivodeshipData, Integer> dataExtractor) {
        List<Integer> data = voivodeshipData
                .stream()
                .map(dataExtractor)
                .sorted()
                .collect(Collectors.toList());
        this.min = data.stream().min(Integer::compareTo).orElse(0);
        this.quantile33 = data.get(data.size()/3);
        this.median = data.get(data.size()/2);
        this.quantile66 = data.get(2*data.size()/3);
        this.max = data.stream().max(Integer::compareTo).orElse(Integer.MAX_VALUE);
        this.average = data.stream()
                .reduce(0, Integer::sum).doubleValue()/data.size();
    }
}
