from concurrent import futures

jobs = {}


def load_urls(callback, urls):
    with futures.ThreadPoolExecutor(max_workers=2) as executor:
        for url in urls:
            executor.submit(callback, url)
            print("fetching " + url)
        print("DONE (fetching)")
    print("excuter done by line")
