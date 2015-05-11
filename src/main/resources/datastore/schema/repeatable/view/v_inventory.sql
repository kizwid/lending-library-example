create view v_inventory as
select
  t.title_name
 ,tt.title_type_name
 ,count(*) num_items
 ,count(l.title_item_id) on_loan
from
 title t
 inner join title_type tt on(t.title_type_id = tt.title_type_id)
 inner join title_item ti on(t.title_id = ti.title_id)
 left outer join loan l on(l.title_item_id = ti.title_item_id)
where
 nvl(l.loan_return_id,1) != 0
group by
 t.title_name
 ,tt.title_type_name
--having count(*) > count(l.title_item_id)
;