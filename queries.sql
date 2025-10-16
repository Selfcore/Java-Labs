-- Вивід замовлень які обробив конкретний офіціант

SELECT O.id, "orderDate", "totalAmount", "fullname" FROM public."Orders" O
JOIN public."Workers" W ON W.id = O."workerId"
WHERE W.fullname = 'Boote Walstow'

-- Усі замовлення обраного клієнта

SELECT C.fullname, O.id AS OrderID, O."orderDate", O."totalAmount" FROM public."Orders" O
JOIN public."Clients" C ON C.id = O."clientId"
WHERE C.fullname = 'Angelina Ponder'

-- Усі напої та десерти

SELECT "name_UA", "name_EN" 
FROM public."Items" I
WHERE I.type = 'напій'

SELECT "name_UA", "name_EN" 
FROM public."Items" I
WHERE I.type = 'десерт'

-- Показати інформацію про барист

SELECT * FROM public."Workers"
WHERE position_id = 1

-- Інформація про офіціантів

SELECT * FROM public."Workers"
WHERE position_id = 2

-- Додати новий вид кави

INSERT INTO public."Items"("name_UA", "name_EN", "type", "price")
VALUES ("Нова кава", "New cofe", "напій", 90)

-- Показати замовлення конкретного десерту

SELECT O.id, O."orderDate", I."name_UA", I."name_EN"
FROM public."Orders" O
JOIN public."OrderItems" OI ON OI."orderId" = O.id
JOIN public."Items" I ON OI."itemId" = I.id
WHERE (I."name_UA" = 'Чізкейк Нью-Йорк' AND I."type" = 'десерт')

SELECT * FROM public."WorkSchedule"

INSERT INTO public."WorkSchedule"("workerId", "dayId", "startTime", "endTime") VALUES
(1, 1, '14:30:00', '18:00:00'),
(1, 2, '12:00:00', '17:30:00')

-- Отримати інформацію про робочий графік певного робітника

SELECT W."fullname", D."day", WS."startTime", WS."endTime"
FROM public."Workers" W
JOIN public."WorkSchedule" WS ON WS."workerId" = W.id
JOIN public."Days" D ON D.id = WS."dayId"
WHERE W."fullname" = 'Nelson Stook'

-- Вибірка інформації про усіх робітників з їх позиціями

SELECT * FROM public."ViewWorkers"

-- Видалення інформації про клієнта по імені

DELETE FROM public."Clients" C
WHERE C.id = (SELECT id FROM public."Clients" C WHERE C.fullname = 'John')

-- Видалення інформації про барист

DELETE FROM public."Workers" W
WHERE W."positionId" = (
  SELECT P.id
  FROM public."Positions" P
  WHERE P.name = 'Бариста'
)

-- Зміна номеру телефону баристи

UPDATE public."Workers" W SET W."phone_number" = '995 634 2145'
WHERE W.id = (
	SELECT id
	FROM public."Positions" P
	WHERE P.name = 'Бариста'
)
