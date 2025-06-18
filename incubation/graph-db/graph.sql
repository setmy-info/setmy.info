CREATE SEQUENCE graph_seq START 1;
CREATE SEQUENCE node_seq START 1;
CREATE SEQUENCE edge_seq START 1;

CREATE TABLE graphs (
    id BIGINT NOT NULL DEFAULT nextval('graph_seq'),
    name VARCHAR(255) NOT NULL,
    grouping_id INTEGER NOT NULL,
    PRIMARY KEY (id, grouping_id),
    UNIQUE(name, grouping_id) -- name is unique in group
) PARTITION BY LIST (grouping_id);

CREATE TABLE graphs_group_1 PARTITION OF graphs FOR VALUES IN (1);
CREATE TABLE graphs_group_2 PARTITION OF graphs FOR VALUES IN (2);

CREATE TABLE nodes (
    id BIGINT NOT NULL DEFAULT nextval('node_seq'),
    graph_id BIGINT NOT NULL,
    grouping_id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
	--metadata JSONB DEFAULT '{}'::jsonb,
    metadata JSONB DEFAULT '{}'::jsonb NOT NULL,
    UNIQUE (graph_id, name), -- name is unique in graph
    PRIMARY KEY (id, grouping_id),
    CONSTRAINT fk_nodes_graph FOREIGN KEY (graph_id, grouping_id)
        REFERENCES graphs (id, grouping_id) ON DELETE CASCADE
);

CREATE TABLE edges (
    id BIGINT NOT NULL DEFAULT nextval('edge_seq'),
    graph_id BIGINT NOT NULL,
    grouping_id INTEGER NOT NULL,
    source_node_id BIGINT NOT NULL,
    target_node_id BIGINT NOT NULL,
    weight DOUBLE PRECISION NOT NULL,
    metadata JSONB DEFAULT '{}'::jsonb NOT NULL,
    PRIMARY KEY (id),
    
    CONSTRAINT fk_edges_graph FOREIGN KEY (graph_id, grouping_id)
        REFERENCES graphs (id, grouping_id) ON DELETE CASCADE,
        
    CONSTRAINT fk_edges_source_node FOREIGN KEY (source_node_id, grouping_id)
        REFERENCES nodes (id, grouping_id) ON DELETE CASCADE,
        
    CONSTRAINT fk_edges_target_node FOREIGN KEY (target_node_id, grouping_id)
        REFERENCES nodes (id, grouping_id) ON DELETE CASCADE
);

CREATE INDEX idx_graphs_grouping ON graphs (grouping_id);

CREATE INDEX idx_nodes_graph ON nodes (graph_id);
CREATE INDEX idx_nodes_grouping ON nodes (grouping_id);
CREATE INDEX idx_nodes_metadata_gin ON nodes USING GIN (metadata);

CREATE INDEX idx_edges_graph ON edges (graph_id);
CREATE INDEX idx_edges_grouping ON edges (grouping_id);
CREATE INDEX idx_edges_metadata_gin ON edges USING GIN (metadata);

--

INSERT INTO graphs (name, grouping_id) VALUES ('TestGraph', 1);
INSERT INTO nodes (graph_id, grouping_id, name, type, metadata)
VALUES
    (1, 1, 'A', 'sensor', '{"value": 10, "unit": "C"}'),
    (1, 1, 'B', 'sensor', '{"value": 20, "unit": "C"}'),
    (1, 1, 'C', 'actuator', '{"status": "on"}');

INSERT INTO edges (graph_id, grouping_id, source_node_id, target_node_id, weight, metadata)
VALUES (1, 1, 1, 2, 1.5, '{"label": "AB"}'),
       (1, 1, 2, 3, 2.5, '{"label": "BC"}'),
	   (1, 1, 1, 3, 3.0, '{"label": "AC"}');

SELECT *
FROM nodes
WHERE graph_id = 1 AND metadata->>'value' IS NOT NULL AND (metadata->>'value')::INT > 15;

SELECT *
FROM edges
WHERE graph_id = 1 AND weight > 2;

SELECT n.*
FROM nodes n
INNER JOIN graphs g
  ON n.graph_id = g.id AND n.grouping_id = g.grouping_id
WHERE g.name = 'TestGraph';

SELECT *
FROM nodes
WHERE graph_id = 1 AND grouping_id = 1;

SELECT e.*
FROM edges e
INNER JOIN graphs g
  ON e.graph_id = g.id AND e.grouping_id = g.grouping_id
WHERE g.name = 'TestGraph' and g.grouping_id = 1;
