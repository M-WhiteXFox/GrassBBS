document.addEventListener('DOMContentLoaded', function () {
    const iconBtn = document.getElementById('ai-icon-btn');

    setTimeout(() => {
        const popup = document.getElementById('ai-popup');
        popup.classList.add('show');
        popup.style.right = '30px';
        popup.style.bottom = '30px';
        popup.style.left = 'auto';
        popup.style.top = 'auto';
        setupPopupDrag();
    }, 1000);

    document.getElementById('ai-input').addEventListener('keypress', function (e) {
        if (e.key === 'Enter') {
            sendAIQuestion();
        }
    });

    iconBtn.addEventListener('click', openAIPopup);

    setupIconDrag();
});

let topicContextSent = false;

async function sendAIQuestion() {
    const input = document.getElementById('ai-input');
    const text = input.value.trim();
    const topicContent = window.topicContent || '';
    if (!text) return;

    const messages = document.getElementById('ai-messages');
    const userMsg = document.createElement('div');
    userMsg.className = 'user-message';
    userMsg.textContent = text;
    messages.appendChild(userMsg);

    const aiMsg = document.createElement('div');
    aiMsg.className = 'ai-message';
    aiMsg.textContent = '思考中...';
    messages.appendChild(aiMsg);

    input.value = '';
    messages.scrollTop = messages.scrollHeight;

    // 构建请求数据
    const payload = {
        message: text,
        topicContent: topicContent
    };

    // 首次发送时带上帖子内容
    if (!topicContextSent && window.topicContent) {
        payload.context = window.topicContent;
        topicContextSent = true;
    }

    try {
        const response = await fetch('/api/ai/chat', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();

        if (data.code === 200 && data.detail) {
            aiMsg.textContent = data.detail;
        } else {
            throw new Error(data.description || '服务器返回了无效的响应格式');
        }
    } catch (error) {
        console.error('AI API调用错误:', error);
        aiMsg.textContent = `抱歉，发生了错误: ${error.message}`;
    }

    messages.scrollTop = messages.scrollHeight;
}

function closeAIPopup() {
    document.getElementById('ai-popup').classList.remove('show');
    document.getElementById('ai-icon-btn').classList.remove('hidden');
    // 不再重设图标位置
}

function openAIPopup() {
    const popup = document.getElementById('ai-popup');
    const iconBtn = document.getElementById('ai-icon-btn');
    const rect = iconBtn.getBoundingClientRect();

    popup.classList.add('show');
    iconBtn.classList.add('hidden');

    popup.style.left = (rect.left + rect.width / 2 - popup.offsetWidth / 2) + 'px';
    popup.style.top = (rect.top - popup.offsetHeight - 10) + 'px';
    popup.style.right = 'auto';
    popup.style.bottom = 'auto';
    popup.style.position = 'fixed';
}

function setupIconDrag() {
    const icon = document.getElementById('ai-icon-btn');

    icon.addEventListener('mousedown', (e) => {
        // Prevent default text selection or other unwanted mousedown behaviors
        e.preventDefault();

        let isDraggingThisMove = false; // Flag to indicate if actual dragging occurred
        const initialRect = icon.getBoundingClientRect(); // Get icon's position when mousedown starts
        const offsetX = e.clientX - initialRect.left;
        const offsetY = e.clientY - initialRect.top;

        const handleMouseMove = (moveEvent) => {
            if (!isDraggingThisMove) {
                // This is the first mousemove after mousedown, so a drag has started.
                // Now, switch to left/top positioning based on its position when mousedown started.
                isDraggingThisMove = true;
                icon.style.left = initialRect.left + 'px';
                icon.style.top = initialRect.top + 'px';
                icon.style.right = 'auto';
                icon.style.bottom = 'auto';
                // icon.style.position = 'fixed'; // Already fixed by CSS, typically not needed here.
                document.body.style.userSelect = 'none'; // Prevent text selection during drag
            }

            // Calculate new position
            let x = moveEvent.clientX - offsetX;
            let y = moveEvent.clientY - offsetY;

            // Constrain to viewport
            x = Math.max(0, Math.min(x, window.innerWidth - icon.offsetWidth));
            y = Math.max(0, Math.min(y, window.innerHeight - icon.offsetHeight));

            icon.style.left = x + 'px';
            icon.style.top = y + 'px';
        };

        const handleMouseUp = () => {
            if (isDraggingThisMove) {
                document.body.style.userSelect = ''; // Restore text selection
            }
            // Clean up listeners
            document.removeEventListener('mousemove', handleMouseMove);
            document.removeEventListener('mouseup', handleMouseUp);
        };

        // Add temporary listeners for the drag operation
        document.addEventListener('mousemove', handleMouseMove);
        document.addEventListener('mouseup', handleMouseUp);
    });
}

function setupPopupDrag() {
    const popup = document.getElementById('ai-popup');
    const header = popup.querySelector('.ai-popup-header');
    let isDragging = false, offsetX = 0, offsetY = 0;

    header.addEventListener('mousedown', (e) => {
        isDragging = true;
        const rect = popup.getBoundingClientRect();
        offsetX = e.clientX - rect.left;
        offsetY = e.clientY - rect.top;
        popup.style.left = rect.left + 'px';
        popup.style.top = rect.top + 'px';
        popup.style.right = 'auto';
        popup.style.bottom = 'auto';
        popup.style.position = 'fixed';
        document.body.style.userSelect = 'none';
    });

    document.addEventListener('mousemove', (e) => {
        if (!isDragging) return;
        let x = e.clientX - offsetX;
        let y = e.clientY - offsetY;
        x = Math.max(0, Math.min(x, window.innerWidth - popup.offsetWidth));
        y = Math.max(0, Math.min(y, window.innerHeight - popup.offsetHeight));
        popup.style.left = x + 'px';
        popup.style.top = y + 'px';
    });

    document.addEventListener('mouseup', () => {
        isDragging = false;
        document.body.style.userSelect = '';
    });
}