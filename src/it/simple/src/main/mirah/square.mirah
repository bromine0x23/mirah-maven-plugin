package test

macro def square(input)
  quote do
    Math.sqrt(Integer.parseInt(`input`))
  end
end

puts square '64'