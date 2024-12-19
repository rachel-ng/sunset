import asyncio
from bleak import BleakClient, BleakScanner

async def data():
    stop_event = asyncio.Event()

    def callback(device, advertising_data):
        if device.name == "Sunset lights":
            print(device)
            print(advertising_data)
            print("") 

    async with BleakScanner(callback) as scanner:
        await stop_event.wait()

def convert_rgb(rgb):
    scale = 0xFF
    adjusted = [max(1, chan) for chan in rgb]
    total = sum(adjusted)
    adjusted = [int(round(chan / total * scale)) for chan in adjusted]

    # Unknown, Red, Blue, Green
    return bytearray([0x1, adjusted[0], adjusted[2], adjusted[1]])

async def main():
    # devices = await BleakScanner.discover()
    device = await BleakScanner.find_device_by_address("D4884E55-80C2-AABC-4651-5652EC06FE9B")
    print(device)
    print(device.details)
    print("\nclient")
    async with BleakClient(device) as client: 
        print(client)
        await client.connect()
        print("connected")
        print(client)
        print("\nservices")
        for service in client.services:
            print(service)
            print(service.uuid)
            print(service.description)
            
            print("\ncharacteristics")
            for characteristic in service.characteristics: 
                print(characteristic)
                print(characteristic.uuid)
                print(characteristic.description)
                for d in characteristic.descriptors: 
                    dd = await client.read_gatt_descriptor(d.handle)
                    print(dd)
                print(characteristic.properties)
                
                if "write-without-response" in characteristic.properties: 
                    print(characteristic.max_write_without_response_size)
                    color = convert_rgb([255, 0, 0])
                
                if "write" in characteristic.properties:
                    print("write")
                    await client.write_gatt_char(characteristic.uuid, bytearray(1), response=False)
                    await asyncio.sleep(1.0)

                    await client.write_gatt_char(characteristic.uuid, bytearray(0), response=False)
                    await asyncio.sleep(1.0)
                    
                print("")
                

        print("\ndisconnect")
        await client.disconnect()
        print("disconnected")



asyncio.run(main())

