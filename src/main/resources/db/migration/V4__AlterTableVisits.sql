ALTER TABLE visits ADD COLUMN date_logged DATETIME NULL DEFAULT CURRENT_TIMESTAMP AFTER reason_for_visit;