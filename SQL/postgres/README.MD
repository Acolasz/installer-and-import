# Function

```shell
-- Function to update the search vector for the 'vehicle' table based on 'rendszam', 'tulajdonos', and related 'adatok'
CREATE OR REPLACE FUNCTION update_vehicle_search_vector()
RETURNS trigger AS $$
DECLARE
adatok_concat TEXT;
BEGIN
  -- Concatenate all related 'adatok' fields from 'vehicle_adatok' table
SELECT string_agg(va.adatok, ' ')
INTO adatok_concat
FROM vehicle_adatok va
WHERE va.vehicle_uuid = NEW.uuid;

-- Update the 'search_vector' using 'rendszam', 'tulajdonos', and concatenated 'adatok'
NEW.search_vector := to_tsvector('hungarian', COALESCE(NEW.rendszam, '') || ' ' || COALESCE(NEW.tulajdonos, '') || ' ' || COALESCE(adatok_concat, ''));

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger to update search_vector before insert or update on the 'vehicle' table
CREATE TRIGGER vehicle_search_vector_trigger
    BEFORE INSERT OR UPDATE ON vehicle
                         FOR EACH ROW EXECUTE FUNCTION update_vehicle_search_vector();

-- Function to update the 'vehicle' search_vector when 'vehicle_adatok' is inserted, updated, or deleted
CREATE OR REPLACE FUNCTION update_vehicle_search_vector_from_adatok()
RETURNS trigger AS $$
DECLARE
adatok_concat TEXT;
BEGIN
  -- Concatenate all related 'adatok' fields from 'vehicle_adatok' table
SELECT string_agg(va.adatok, ' ')
INTO adatok_concat
FROM vehicle_adatok va
WHERE va.vehicle_uuid = COALESCE(NEW.vehicle_uuid, OLD.vehicle_uuid);

-- Update the 'search_vector' in the 'vehicle' table using 'rendszam', 'tulajdonos', and concatenated 'adatok'
UPDATE vehicle
SET search_vector = to_tsvector('hungarian', COALESCE(rendszam, '') || ' ' || COALESCE(tulajdonos, '') || ' ' || COALESCE(adatok_concat, ''))
WHERE uuid = COALESCE(NEW.vehicle_uuid, OLD.vehicle_uuid);

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger to update the search vector in 'vehicle' when 'vehicle_adatok' is inserted, updated, or deleted
CREATE TRIGGER vehicle_adatok_search_vector_trigger
    AFTER INSERT OR UPDATE OR DELETE ON vehicle_adatok
    FOR EACH ROW EXECUTE FUNCTION update_vehicle_search_vector_from_adatok();
```

```shell
@Query(value = "SELECT v.* " +
        "FROM vehicle v " +
        "WHERE v.search_vector @@ plainto_tsquery('hungarian', :q)",
        nativeQuery = true)
List<Vehicle> searchByKeyword(@Param("q") String q);
```