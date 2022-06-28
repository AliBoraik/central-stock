CREATE TABLE IF NOT EXISTS public.product(inner_id uuid not null primary key);
ALTER TABLE public.product ADD COLUMN IF NOT EXISTS frozen_count float8 default 0 not null;