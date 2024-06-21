local key = KEYS[1]
local count = tonumber(ARGV[1])
local time = tonumber(ARGV[2])
local current = redis.call('get', key);
if current and tonumber(current) > count then
    return tonumber(current);
end
--用于对存储在键中的字符串表示的整数值进行原子增减操作。
current = redis.call('incr', key)
if tonumber(current) == 1 then
    redis.call('expire', key, time)
end
return tonumber(current);