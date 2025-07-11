package com.mycompany.projeto1;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.util.Map;

public class BlockRepresenter extends Representer {

    public BlockRepresenter(DumperOptions options) {
        super(options);
    }

    @Override
    protected Node representSequence(Tag tag,
            Iterable<?> sequence,
            DumperOptions.FlowStyle flowStyle) {
        return super.representSequence(tag, sequence, DumperOptions.FlowStyle.BLOCK);
    }

    @Override
    protected Node representMapping(Tag tag,
            Map<?, ?> mapping,
            DumperOptions.FlowStyle flowStyle) {
        // Idem para mapas
        return super.representMapping(tag, mapping, DumperOptions.FlowStyle.BLOCK);
    }
}
